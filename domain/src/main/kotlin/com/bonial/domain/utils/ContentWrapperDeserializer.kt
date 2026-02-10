package com.bonial.domain.utils

import com.bonial.domain.model.network.response.BrochureDto
import com.bonial.domain.model.network.response.ContentWrapperDto
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonDeserializationContext
import java.lang.reflect.Type

/**
 * Deserializer to help with getting one content object which can be an array or a
 * single object depending upon the content type
 */
class ContentWrapperDeserializer : JsonDeserializer<ContentWrapperDto> {

    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): ContentWrapperDto {

        val obj = json.asJsonObject

        val contentType = obj.get("contentType")?.asString

        // 🔴 filter unwanted content types early
        if (contentType != "brochure" && contentType != "brochurePremium") {
            return ContentWrapperDto(
                contentType = contentType,
                content = emptyList()
            )
        }

        val contentElement = obj.get("content")

        val brochures = when {
            contentElement == null || contentElement.isJsonNull ->
                emptyList<BrochureDto>()

            contentElement.isJsonObject ->
                listOf(
                    context.deserialize(contentElement, BrochureDto::class.java)
                )

            contentElement.isJsonArray ->
                contentElement.asJsonArray.map {
                    context.deserialize(it, BrochureDto::class.java)
                }

            else -> emptyList()
        }

        return ContentWrapperDto(
            contentType = contentType,
            content = brochures
        )
    }
}