package DesafioTotem
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

    class FastFoodTest {
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

        @Test
        fun testEditarItem() {
            val item = FastFood.ItemCardapio("1", "X-burger", 10.0)
            fastFood.adicionarAoCarrinho("usuario1", item, 2, 20.0)
            fastFood.editarItem("usuario1", "1", 3)
            val carrinho = fastFood.getCarrinho("usuario1")
            assertEquals(1, carrinho.size)
            assertEquals(item, carrinho[0].first)
            assertEquals(3, carrinho[0].second)
            assertEquals(20.0, carrinho[0].third, 0.001)
        }

        @Test
        fun testExcluirItem() {
            val item = FastFood.ItemCardapio("1", "X-burger", 10.0)
            fastFood.adicionarAoCarrinho("usuario1", item, 2, 20.0)
            fastFood.excluirItem("usuario1", "1")
            val carrinho = fastFood.getCarrinho("usuario1")
            assertEquals(0, carrinho.size)
        }

        @Test
        fun testCalcularValorTotal() {
            val item1 = FastFood.ItemCardapio("1", "X-burger", 10.0)
            val item2 = FastFood.ItemCardapio("2", "X-salada", 12.0)
            fastFood.adicionarAoCarrinho("usuario1", item1, 2, 20.0)
            fastFood.adicionarAoCarrinho("usuario1", item2, 3, 36.0)
            val valorTotal = fastFood.calcularValorTotal("usuario1")
            assertEquals(56.0, valorTotal, 0.001)
        }
    }
