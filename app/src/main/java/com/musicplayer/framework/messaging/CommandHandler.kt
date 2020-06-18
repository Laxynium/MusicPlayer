package com.musicplayer.framework.messaging

import arrow.core.Either

interface CommandHandler<TCommand: Command>{
    suspend fun handle(command:TCommand):Either<Error,Unit>
}