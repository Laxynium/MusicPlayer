package com.musicplayer.musicPlaying.domain

import arrow.core.Either
import arrow.core.Option
import java.util.*

typealias CurrentSongRemoved = Boolean
typealias LastSongRemoved = Boolean
typealias RemovalResult = Pair<CurrentSongRemoved, LastSongRemoved>

class Queue() {
    private var currentSongIndex = 0
    private val songs: MutableList<Song> = mutableListOf()

    private constructor(state:QueueState) : this() {
        this.currentSongIndex = state.currentSongIndex
        state.songs.forEach {
            this.songs.add(it)
        }
    }
    data class QueueState(val currentSongIndex:Int, val songs: List<Song>)
    fun getState() = QueueState(currentSongIndex, songs)
    companion object Creator{
        fun fromState(state:QueueState) = Queue(state)
    }

    fun asList():List<Song> = songs.toList()
    fun currentSong() = Option.fromNullable(songs.getOrNull(currentSongIndex))

    fun enqueue(song:Song){
        songs.add(song)
    }

    fun enqueueAsNext(song:Song) {
        if(songs.isEmpty()){
            enqueue(song)
        } else {
            songs.add(currentSongIndex + 1, song)
        }
    }

    fun goToNext() {
        if(songs.size > 0)
            currentSongIndex =  (currentSongIndex + 1) % songs.size
    }

    fun goToPrev(){
        if(songs.size > 0)
            currentSongIndex = (songs.size + currentSongIndex - 1) % songs.size
    }

    fun goTo(songPosition: Int): Either<String,Unit>{
        val song = songs.getOrNull(songPosition) ?: return Either.left("Song not found")
        currentSongIndex = songPosition
        return Either.right(Unit)
    }

    fun enqueuePlaylist(songs: List<Song>) {
        this.songs.clear()
        this.songs.addAll(songs)
        this.currentSongIndex = 0
    }

    fun removeSong(songId: UUID, position: Int?):RemovalResult {
        val songsToRemove = songs.mapIndexed { i, song -> Pair(i,song) }.filter { p ->
            p.second.id == songId && (position == null ||  p.first == position)
        }
        val currentSongRemoved = songsToRemove
            .any { it.first == currentSongIndex }

        val indexFix = songsToRemove.filter{ p -> p.first < currentSongIndex}.count()

        songs.removeAll(songsToRemove.map { it.second })

        currentSongIndex -= indexFix
        val lastSongRemoved = songs.size == 0
        if(currentSongRemoved){
            if(!lastSongRemoved){
                currentSongIndex  %= songs.size
            }else{
                currentSongIndex = 0
            }
        }

        return RemovalResult(currentSongRemoved, lastSongRemoved)
    }
}