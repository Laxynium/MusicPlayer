package com.musicplayer.framework.messaging

import org.koin.core.Koin
import kotlin.reflect.full.createType

class MessageBus(private val koin: Koin){
    fun <TCommand: Command> dispatch(command:TCommand) {
        val commandHandlers = koin.getAll<CommandHandler<TCommand>>()
        val handler =
            commandHandlers.first { h -> h::class.supertypes[0].arguments[0].type == command::class.createType() }
        handler.handle(command)
    }
    fun <TQuery: Query<TResult>, TResult> dispatch(query:TQuery): TResult? {
        val queryHandlers = koin.getAll<QueryHandler<TQuery, TResult>>()
        val queryHandler =
            queryHandlers.first { h -> h::class.supertypes[0].arguments[0].type == query::class.createType() }
        return queryHandler.handle(query)
    }
}