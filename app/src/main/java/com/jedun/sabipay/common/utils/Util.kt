package com.jedun.sabipay.common.utils

import java.net.URLEncoder
import java.nio.charset.StandardCharsets

fun encodedUrl(url: String): String {
    return URLEncoder.encode(url, StandardCharsets.UTF_8.toString())
}