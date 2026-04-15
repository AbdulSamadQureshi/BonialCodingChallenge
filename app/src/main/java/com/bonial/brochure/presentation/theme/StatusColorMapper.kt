package com.bonial.brochure.presentation.theme

import androidx.compose.ui.graphics.Color

/**
 * All status-related colours in one place.
 *
 * Open/Closed: to support a new status value, add one entry here.
 * Neither [StatusBadge] (list screen) nor [StatusChip] (detail screen) needs
 * to change — they both delegate to this mapper.
 */
data class StatusColorSet(
    /** Dot / solid indicator colour. */
    val dot: Color,
    /** Chip background (soft tint). Used on the detail screen. */
    val background: Color,
    /** Readable label colour on top of [background]. Used on the detail screen. */
    val label: Color,
)

/**
 * Maps a raw status string (case-insensitive) to its [StatusColorSet].
 * Unknown or null values fall back to the neutral grey palette.
 */
fun String?.toStatusColorSet(): StatusColorSet = when (this?.lowercase()) {
    "alive" -> StatusColorSet(dot = StatusAlive, background = StatusAliveBg, label = StatusAliveText)
    "dead"  -> StatusColorSet(dot = StatusDead,  background = StatusDeadBg,  label = StatusDeadText)
    else    -> StatusColorSet(dot = StatusUnknown, background = StatusUnknownBg, label = StatusUnknownText)
}
