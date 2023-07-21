package DesafioTotem
fun main(){
        val fastFood = FastFood()
        val totem = Totem(fastFood)

        println("====================================")
        println("Olá...Digite seu nome para o pedido:")
        val usuario = readLine() ?: "Cliente Anônimo"

        val codigoPedido = fastFood.gerarCodigoPedido()

        totem.fazerPedido(usuario,codigoPedido)

        println("Obrigado por usar o FastFood! Volte sempre!")
    }
