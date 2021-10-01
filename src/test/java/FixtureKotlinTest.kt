import io.github.simplefixture.Fixture
import org.junit.Test
import org.junit.jupiter.api.Assertions
import sample.Sample

class FixtureKotlinTest {
    @Test
    fun testBasicFixture(){
        val f = Fixture()

        val sample = f.create(Sample::class.java)

        Assertions.assertEquals(sample.name, "name1")
    }
}