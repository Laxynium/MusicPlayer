package com.musicplayer.framework.messaging

interface CommandHandler<TCommand: Command>{
    fun handle(command:TCommand)
}