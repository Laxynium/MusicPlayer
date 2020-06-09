package com.musicplayer.framework.messaging

interface QueryHandler<TQuery: Query<TResult>, TResult>{
    suspend fun handle(query:TQuery):TResult
}