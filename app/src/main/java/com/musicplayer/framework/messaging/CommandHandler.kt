package com.musicplayer.framework.messaging

interface CommandHandler<TCommand: Command>{
    suspend fun handle(command:TCommand)
}