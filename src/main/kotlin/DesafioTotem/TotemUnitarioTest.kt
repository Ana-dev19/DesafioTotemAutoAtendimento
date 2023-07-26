package DesafioTotem
import org.junit.jupiter.api.Test

class TotemUnitarioTest {

        private val fastFood = FastFood()
        private val totem = Totem(fastFood)

        @Test
        fun testMostrarMenuInicial() {

            // Testa se o método mostrarMenuInicial imprime as opções corretas
            val esperado = "\n===== Totem de Autoatendimento =====\n" +
                    "   1 ⇾ Lanche\n" +
                    "   2 ⇾ Bebida\n" +
                    "   3 ⇾ Sobremesa\n" +
                    "   4 ⇾ Finalizar Pedido\n" +
                    "   5 ⇾ Editar Item do Carrinho\n" +
                    "   6 ⇾ Excluir Item do Carrinho\n" +
                    "   7 ⇾ Sair\n" +
                    "===================================\n" +
                    "Escolha uma opção            assertEquals(esperado, totem.mostrarMenuInicial(\"usuario\"))\n:\n"
        }

        @Test
        fun testMostrarCardapioLanches() {

            // Testa se o método mostrarCardapioLanches imprime os lanches do cardápio
            val esperado = "===== Lanches =====\n" +
                    "1. X-Burger - R$ 10.0\n" +
                    "2. X-Salada - R$ 12.0\n" +
                    "====================\n" +
                    "Escolha o número do lanche que deseja comprar:\n"
        }

    }



