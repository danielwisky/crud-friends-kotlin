package br.com.danielwisky.friends

import io.mockk.MockKAnnotations
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
abstract class UnitTest {

    @BeforeEach
    fun setUp() = MockKAnnotations.init(this)
}
