package be.vives.philipshue

import com.android.volley.NetworkResponse
import com.android.volley.ParseError
import com.android.volley.Response
import com.android.volley.toolbox.HttpHeaderParser
import com.android.volley.toolbox.JsonRequest
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.UnsupportedEncodingException

class MyJsonArrayRequest(method: Int, url: String, jsonRequest: JSONObject, listener: Response.Listener<JSONArray> , errorListener: Response.ErrorListener ) : JsonRequest<JSONArray>(method, url, jsonRequest?.toString(), listener, errorListener)
{

    override fun parseNetworkResponse(response: NetworkResponse): Response<JSONArray> {
        try {
            var jsonString = String(
                response.data,
                charset(HttpHeaderParser.parseCharset(response.headers, PROTOCOL_CHARSET))
            );
            return Response.success(
                JSONArray(jsonString),
                HttpHeaderParser.parseCacheHeaders(response)
            );
        } catch (e: UnsupportedEncodingException) {
            return Response.error(ParseError(e));
        } catch (je: JSONException) {
            return Response.error(ParseError(je));
        }
    }
}