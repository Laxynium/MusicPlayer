package com.musicplayer.framework

interface CommandHandler<TCommand: Command>{
    fun handle(command:TCommand)
}