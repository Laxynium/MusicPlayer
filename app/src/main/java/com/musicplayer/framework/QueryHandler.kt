package com.musicplayer.framework

interface QueryHandler<TQuery:Query<TResult>, TResult>{
    fun handle(query:TQuery):TResult
}