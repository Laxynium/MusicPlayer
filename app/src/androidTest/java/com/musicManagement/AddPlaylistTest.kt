package com.musicManagement

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.SetupDb
import com.musicplayer.framework.messaging.MessageBus
import com.musicplayer.musicManagement.models.Playlist
import com.musicplayer.musicManagement.regularPlaylist.CreateRegularPlaylist
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.KoinTest
import org.koin.test.inject
import java.util.*


@RunWith(AndroidJUnit4::class)
@MediumTest
class AddPlaylistTest: KoinTest, SetupDb {
    private val messageBus: MessageBus by inject()
    private val testUUID = UUID.fromString("00000000-0000-0000-0000-00000000000")

    @Test
    fun verify() {
        messageBus.dispatch(CreateRegularPlaylist(testUUID, "mojaNowa"))
        val a = "S"
    }


}