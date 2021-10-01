package sample

import io.github.simplefixture.Fixture
import java.time.ZonedDateTime


data class Sample(
    val subSample : SubSample,
    val name: String,
    val category: String
)

data class SubSample(
    val payloadL: ByteArray,
    val eventtime: ZonedDateTime?
)