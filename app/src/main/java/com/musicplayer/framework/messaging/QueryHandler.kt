package com.musicplayer.framework.messaging

interface QueryHandler<TQuery: Query<TResult>, TResult>{
    fun handle(query:TQuery):TResult
}