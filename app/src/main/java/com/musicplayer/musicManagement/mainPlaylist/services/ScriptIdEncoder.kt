package com.musicplayer.musicManagement.mainPlaylist.services

import org.mozilla.javascript.Context
import org.mozilla.javascript.Function

class ScriptIdEncoder{
    suspend fun encode(scriptId: String): String{
        val script = """
            function i(t, e) {
    if (-1 < t.indexOf("/")) {
        t = t.split("/");
        for (var r = 0, n = ""; r < t.length; r++)
            n += String.fromCharCode(t[r]);
        return "s" == e ? n : parseInt(n)
    }
    return "s" == e ? String.fromCharCode(t) : parseInt(String.fromCharCode(t))
}
function callMe(SomeT) {
    for (var e = 0, r = 0, n = ""; r < SomeT.length; r++) {
        if (e = SomeT.charCodeAt(r),
            i("54/52", "n") < e && e < i("57/49", "n"))
            e = e == i("54/53", "n") ? i("57/48", "n") : e - 1;
        else if (i("57/54", "n") < e && e < i("49/50/51", "n"))
            e = e == i("49/50/50", "n") ? i("57/55", "n") : e + 1;
        else if (i("52/55", "n") < e && e < i("53/51", "n"))
            switch (e) {
                case i("52/56", "n"):
                    e = i("53/55", "n");
                    break;
                case i("52/57", "n"):
                    e = i("53/54", "n");
                    break;
                case i("53/48", "n"):
                    e = i("53/53", "n");
                    break;
                case i("53/49", "n"):
                    e = i("53/52", "n");
                    break;
                case i("53/50", "n"):
                    e = i("53/51", "n")
            }
        else
            i("53/50", "n") < e && e < i("53/56", "n") ? e = Math.round(i(e.toString()) / 2).toString().charCodeAt(0) : e == i("52/53", "n") && (e = i("57/53", "n"));
        n += String.fromCharCode(e)
    }
    return n
};
        """.trimIndent()

        val context = Context.enter()
        context.optimizationLevel = -1
        try {
            val scope = context.initStandardObjects()
            context.evaluateString(scope, script, "script", 1, null)
            val fct = scope["callMe", scope] as Function
            val result: Any = fct.call(
                context, scope, scope, arrayOf<Any>(scriptId)
            )
            return result.toString()
        }catch (ex:Exception){
            println(ex)
        }
        finally {
            Context.exit()
        }
        throw Exception("Problem with encoding script id")
    }

}