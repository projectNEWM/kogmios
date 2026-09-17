package io.newm.kogmios.protocols.model.fault
import com.fasterxml.jackson.annotation.JsonTypeInfo

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.EXISTING_PROPERTY,
    property = "code",
    visible = true,
)
sealed interface Fault {
    val code: Long

    val message: String

    val data: FaultData?
}
