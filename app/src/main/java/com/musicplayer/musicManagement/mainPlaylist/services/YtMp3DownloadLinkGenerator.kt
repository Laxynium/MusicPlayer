package com.musicplayer.musicManagement.mainPlaylist.services

import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.github.kittinunf.fuel.httpGet
import kotlinx.coroutines.delay
import org.jsoup.Jsoup
import java.time.Duration
import java.time.LocalDateTime

class YtMp3DownloadLinkGenerator(private val encoder: ScriptIdEncoder){
    private val servers = mapOf(
        1 to "fff",
        2 to "ffr",
        3 to "fij",
        4 to "flr",
        5 to "frr",
        6 to "fti",
        7 to "ftl",
        8 to "ift",
        9 to "iil",
        10 to "iir",
        11 to "ijl",
        12 to "ijt",
        13 to "ilj",
        14 to "irf",
        15 to "itj",
        16 to "jif",
        17 to "jjf",
        18 to "jjj",
        19 to "jli",
        20 to "jrt",
        21 to "jti",
        22 to "lii",
        23 to "lit",
        24 to "ljf",
        25 to "ljt",
        26 to "llj",
        27 to "lrf",
        28 to "ltl",
        29 to "rff",
        30 to "rft",
        31 to "rif",
        32 to "rjj",
        33 to "rjl",
        34 to "rri",
        35 to "rtf",
        36 to "rtr",
        37 to "rtt",
        38 to "tfj",
        39 to "tft",
        40 to "tjj",
        41 to "tlf",
        42 to "ttj",
        43 to "tif",
        44 to "ftt",
        45 to "llt",
        46 to "jrr"
    )

    private var serverNotFoundYet: Boolean = false
    suspend fun generateLink(musicIdFromYoutube: String):String{
        val response =  getResponse(musicIdFromYoutube)
        val parsedResponse = parseResponse(response)

        if(!servers.containsKey(parsedResponse.first))
        {
            throw Exception("Could not find server with id '${parsedResponse.first}' in dictionary.");
        }

        val hash = parsedResponse.second
        var sid = parsedResponse.first
        if(serverNotFoundYet)
        {
            sid = progress(hash)
        }

        return "https://${servers[sid]}.fjrifj.frl/${hash}/${musicIdFromYoutube}"
    }

    private suspend fun progress(hash: String): Int{
        println("In progress")
        val url = "https://i.fjrifj.frl/progress.php?callback=jQuery33109514798167395044_1532263442793&id=${hash}&_1532263442795"
        val request = url.httpGet()
            .appendHeader("Referer", "https://ytmp3.cc")
            .appendHeader("User-Agent", "PostmanRuntime/7.1.5")

        val startTime = LocalDateTime.now()
        val maxTime = Duration.ofMinutes(1)

        while (Duration.between(
                startTime,
                LocalDateTime.now()
            ) <= maxTime){
            val response = request.response()
            val content = response.second.body().asString("application/javascript")

            val beg = content.indexOf("{")
            val end = content.indexOf("}")

            if (beg == -1 || end == -1)
            {
                //Means server couldn't handle request as we expected.
                throw Exception("ytmp3.cc server couldn\'t handle our request.\nReturned error:${content}")
            }

            val json = content.substring(beg, end+1)
            val obj = Parser.default()
                .parse(StringBuilder(json)) as JsonObject

            val sid = obj.string("sid")
            val progress = obj.string("progress")

            if(progress == "3"){
                return sid?.toIntOrNull()?: throw Exception("Invalid sid")
            }
            delay(3000)
        }
        throw Exception("Exceeded limit of time for download.")
    }

    private suspend fun getResponse(musicIdFromYoutube: String): String {
        val scriptId = getScriptId()
        val encodedScriptId = encoder.encode(scriptId)

        val url = "https://i.fjrifj.frl/check.php?callback=jQuery3310147368603350448_1558729784771&v=${musicIdFromYoutube}&f=mp3&k=${encodedScriptId}&_=1558729784773"

        val response = url.httpGet()
            .appendHeader("Referer", "https://ytmp3.cc")
            .appendHeader("User-Agent", "PostmanRuntime/7.1.5")
            .response()

        return response.second.body().asString("application/javascript")
    }

    private fun parseResponse(response: String): Pair<Int, String> {
        val beg = response.indexOf('{')
        val end = response.indexOf('}')

        if (beg == -1 || end == -1)
        {
            //Means server couldn't handle request as we expected.
            throw Exception("ytmp3.cc server couldn\'t handle our request.\nReturned error:${response}")
        }

        val json = response.substring(beg, end+1)
        val obj = Parser.default()
            .parse(StringBuilder(json)) as JsonObject

//        val info =  JsonToken.
        val sid = obj.string("sid")
        val hash = obj.string("hash")
        val sidAsInt = sid?.toIntOrNull()
        if(sidAsInt == null || sidAsInt == 0){
            serverNotFoundYet = true
        }

        return Pair(sidAsInt ?: 1, hash?:"")
    }


    private fun getScriptId():String{
        val doc = Jsoup.connect("https://ytmp3.cc").get()
        val scripts= doc.select("script")[1]
        val scriptId = scripts.attr("src")
        val match = "[a-z]{1}\\=[a-zA-Z0-9\\-\\\\_]{16,40}".toRegex(RegexOption.IGNORE_CASE).find(scriptId)
        val value = match?.value

        return value?.substring(2)?: throw Exception("Regex didn't work")
    }
}