package com.rabbah.clw.services

import android.nfc.cardemulation.HostApduService
import android.os.Bundle
import android.util.Log
import java.nio.charset.Charset

class NfcPaymentService : HostApduService() {

    companion object {
        // This static variable holds the data you want to share (set from your UI)
        var dataToSend: String = ""
    }

    // 1. Defined in your Reader App (NfcReaderManager.kt)
    // CLA=0x80, INS=0x10
    private val GET_DATA_APDU_HEADER = "8010"

    override fun processCommandApdu(commandApdu: ByteArray, extras: Bundle?): ByteArray {
        if (commandApdu.isEmpty()) return STATUS_FAILED

        val hexCommand = toHex(commandApdu)
        Log.d("HCE", "Received APDU: $hexCommand")

        // 1. Check for SELECT AID (System handles routing, but we must respond 9000)
        if (isSelectAidApdu(commandApdu)) {
            Log.d("HCE", "AID Selected. Reader connected.")
            return STATUS_SUCCESS
        }

        // 2. Check for GET DATA Command (80 10 00 00 00)
        if (hexCommand.startsWith(GET_DATA_APDU_HEADER)) {
            Log.d("HCE", "Reader requested data. Sending: $dataToSend")

            val responseData = dataToSend.toByteArray(Charsets.UTF_8)

            // Return [Data Bytes] + [90 00]
            return responseData + STATUS_SUCCESS
        }

        return STATUS_UNKNOWN
    }

    override fun onDeactivated(reason: Int) {
        Log.d("HCE", "Deactivated: $reason")
    }

    // --- Helpers ---
    private val STATUS_SUCCESS = byteArrayOf(0x90.toByte(), 0x00.toByte())
    private val STATUS_FAILED = byteArrayOf(0x6F.toByte(), 0x00.toByte())
    private val STATUS_UNKNOWN = byteArrayOf(0x6D.toByte(), 0x00.toByte())

    private fun isSelectAidApdu(apdu: ByteArray): Boolean {
        return apdu.size >= 4 && apdu[0] == 0x00.toByte() && apdu[1] == 0xA4.toByte()
    }

    private fun toHex(bytes: ByteArray): String = bytes.joinToString("") { "%02X".format(it) }
}