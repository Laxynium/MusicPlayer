package com.musicplayer.framework.messaging

import arrow.core.Either
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.Koin
import kotlin.reflect.full.createType

class MessageBus(private val koin: Koin){
    suspend fun <TCommand: Command> dispatch(command:TCommand):Either<Error,Unit> = withContext(Dispatchers.IO){
        val commandHandlers = koin.getAll<CommandHandler<TCommand>>()
        val handler =
            commandHandlers.firstOrNull { h -> h::class.supertypes[0].arguments[0].type == command::class.createType() }
        handler?.handle(command) ?: throw IllegalArgumentException("Handler for given command $command was not found")
    }
    fun <TQuery: Query<TResult>, TResult> dispatch(query:TQuery): TResult? {
        val queryHandlers = koin.getAll<QueryHandler<TQuery, TResult>>()
        val queryHandler =
            queryHandlers.firstOrNull { h -> h::class.supertypes[0].arguments[0].type == query::class.createType() }
        return queryHandler?.handle(query) ?: throw IllegalArgumentException("Handler for given query $query was not found")
    }
}