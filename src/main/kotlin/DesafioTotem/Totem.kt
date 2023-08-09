package DesafioTotem

import kotlin.system.exitProcess

class Totem(private val fastFood: FastFood){

    fun mostrarMenuInicial(usuario: String) {
        println("\n===== Totem de Autoatendimento =====")
        println("   1 ⇾ Lanche")
        println("   2 ⇾ Bebida")
        println("   3 ⇾ Sobremesa")
        println("   4 ⇾ Finalizar Pedido")
        println("   5 ⇾ Editar Item do Carrinho")
        println("   6 ⇾ Excluir Item do Carrinho")
        println("   7 ⇾ Sair")
        println("===================================")
        println("Escolha uma opção:")
        val escolha = try {
            readLine()?.toInt() ?: 0
        } catch (e: NumberFormatException) {
            println("Formato inválido, para escolher o item, você deve informar o número dele")
            mostrarMenuInicial(usuario)
            return
        }

        when (escolha) {
            1 -> mostrarCardapioLanches(usuario)
            2 -> mostrarCardapioBebidas(usuario)
            3 -> mostrarCardapioSobremesas(usuario)
            4 -> finalizarPedido(usuario)
            5 -> editarItem(usuario)
            6 -> excluirItem(usuario)
            7 -> {
                println("Saindo do Totem de Autoatendimento...")
                exitProcess(0)
            }
            else -> {
                println("Opção inválida, tente novamente")
                mostrarMenuInicial(usuario)
            }
        }
    }

    fun mostrarCardapioLanches(usuario: String) {
        println("===== Lanches =====")
        for ((codigo, item) in fastFood.cardapioLanches) {
            println("$codigo. ${item.nome} - R$ ${item.preco}")
        }
        println("====================")
        println("Escolha o número do lanche que deseja comprar:")
        val escolha = readLine()
        if (escolha.isNullOrBlank() || !fastFood.cardapioLanches.containsKey(escolha)) {
            println("Opção inválida, tente novamente")
            mostrarCardapioLanches(usuario)
        } else {
            val itemEscolhido = fastFood.cardapioLanches[escolha]?.nome ?: ""
            selecionarQuantidade(usuario, itemEscolhido, fastFood.cardapioLanches[escolha]?.preco ?: 0.0)
        }
    }

    private fun mostrarCardapioBebidas(usuario: String) {
        println("===== Bebidas =====")
        for ((codigo, item) in fastFood.cardapioBebidas) {
            println("$codigo. ${item.nome} - R$ ${item.preco}")
        }
        println("====================")
        println("Escolha o número da bebida que deseja comprar:")
        val escolha = readLine()
        if (escolha.isNullOrBlank() || !fastFood.cardapioBebidas.containsKey(escolha)) {
            println("Opção inválida, tente novamente")
            mostrarCardapioBebidas(usuario)
        } else {
            val itemEscolhido = fastFood.cardapioBebidas[escolha]?.nome ?: ""
            selecionarQuantidade(usuario, itemEscolhido, fastFood.cardapioBebidas[escolha]?.preco ?: 0.0)
        }
    }

    private fun mostrarCardapioSobremesas(usuario: String) {
        println("===== Sobremesas =====")
        for ((codigo, item) in fastFood.cardapioSobremesas) {
            println("$codigo. ${item.nome} - R$ ${item.preco}")
        }
        println("====================")
        println("Escolha o número da sobremesa que deseja comprar:")
        val escolha = readLine()
        if (escolha.isNullOrBlank() || !fastFood.cardapioSobremesas.containsKey(escolha)) {
            println("Opção inválida, tente novamente")
            mostrarCardapioSobremesas(usuario)
        } else {
            val itemEscolhido = fastFood.cardapioSobremesas[escolha]?.nome ?: ""
            selecionarQuantidade(usuario, itemEscolhido, fastFood.cardapioSobremesas[escolha]?.preco ?: 0.0)
        }
    }

    fun selecionarQuantidade(usuario: String, itemEscolhido: String, preco: Double) {
        println("Digite a quantidade:")
        val quantidade = readLine()?.toIntOrNull()

        if (quantidade != null && quantidade > 0) {
            fastFood.adicionarAoCarrinho(usuario, FastFood.ItemCardapio("", itemEscolhido, preco), quantidade, preco)
            mostrarCarrinho(usuario)
        } else {
            println("Quantidade inválida, tente novamente")
            selecionarQuantidade(usuario, itemEscolhido, preco)
        }
    }

    fun editarItem(usuario: String) {
        println("Digite o código do item que deseja editar:")
        val codigo = readLine() ?: ""
        println("Digite a nova quantidade:")
        val novaQuantidade = readLine()?.toIntOrNull()

        if (novaQuantidade != null && novaQuantidade > 0) {
            fastFood.editarItem(usuario, codigo, novaQuantidade)
            mostrarCarrinho(usuario)
        } else {
            println("Quantidade inválida, tente novamente")
            editarItem(usuario)
        }
    }

    fun excluirItem(usuario: String) {
        println("Digite o código do item que deseja excluir:")
        val codigo = readLine() ?: ""

        fastFood.excluirItem(usuario, codigo)
        mostrarCarrinho(usuario)
    }

    private fun finalizarPedido(usuario: String) {
        val total = fastFood.calcularValorTotal(usuario)
        println("===== Carrinho de Compras =====")
        val carrinho = fastFood.getCarrinho(usuario)
        for ((item, quantidade, preco) in carrinho) {
            println("$quantidade x ${item.nome} - R$ ${quantidade * preco}")
        }
        println("===================================")
        println("Valor total do pedido: R$ $total")
        println("Forma de pagamento:")
        println("1 ⇾ Cartão de Crédito")
        println("2 ⇾ Cartão de Débito")
        println("3 ⇾ Vale Refeição")
        println("4 ⇾ Dinheiro")
        println("===================================")
        val escolha = try {
            readLine()?.toInt() ?: 0
        } catch (e: NumberFormatException) {
            println("Opção inválida, tente novamente")
            finalizarPedido(usuario)
            return
        }

        when (escolha) {
            1, 2, 3 -> {
                println("Compra finalizada com sucesso! Boa refeição.")
                exitProcess(0)
            }
            4 -> {
                println("Digite o valor em dinheiro:")
                val dinheiro = try {
                    readLine()?.toDouble() ?: 0.0
                } catch (e: NumberFormatException) {
                    println("Valor inválido, tente novamente")
                    finalizarPedido(usuario)
                    return
                }

                if (dinheiro >= total) {
                    val troco = dinheiro - total
                    println("Troco: R$ $troco")
                    println("Compra finalizada com sucesso! Boa refeição.")
                    exitProcess(0)
                } else {
                    println("Valor insuficiente para pagar o pedido.")
                    finalizarPedido(usuario)
                }
            }
            else -> {
                println("Opção inválida, tente novamente")
                finalizarPedido(usuario)
            }
        }
    }

    private fun mostrarCarrinho(usuario: String) {
        println("===== Carrinho de Compras =====")
        val carrinho = fastFood.getCarrinho(usuario)
        for ((item, quantidade, preco) in carrinho) {
            println("$quantidade x ${item.nome} - R$ ${quantidade * preco}")
        }
        println("===============================")
        val total = fastFood.calcularValorTotal(usuario)
        println("Valor total do pedido: R$ $total")
        println("===================================")
        mostrarMenuInicial(usuario)
    }

    fun fazerPedido(usuario: String, codigoPedido: String) {
    println("Seu código de pedido é: $codigoPedido")

        while (true) {
            mostrarMenuInicial(usuario)

            println("Deseja fazer outro pedido? (Sim/Não")
            val resposta = readLine()?.toUpperCase()
            if (resposta != "Sim"){
                println("\nVolte sempre!")
                break
            }
        }
    }
}
