package DesafioTotem
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class FastFoodIntegradoTest{


        private val fastFood = FastFood()

        @Test
        fun testAdicionarAoCarrinho() {
            val item = FastFood.ItemCardapio("1", "X-burger", 10.0)
            fastFood.adicionarAoCarrinho("usuario1", item, 2, 20.0)
            val carrinho = fastFood.getCarrinho("usuario1")
            assertEquals(1, carrinho.size)
            assertEquals(item, carrinho[0].first)
            assertEquals(2, carrinho[0].second)
            assertEquals(20.0, carrinho[0].third, 0.001)
        }
    }
