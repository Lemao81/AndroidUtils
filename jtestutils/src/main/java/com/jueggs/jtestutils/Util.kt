import kotlinx.coroutines.runBlocking
import org.mockito.BDDMockito

object Util {
    fun <T> givenSuspended(block: suspend () -> T) = BDDMockito.given(runBlocking { block() })
}