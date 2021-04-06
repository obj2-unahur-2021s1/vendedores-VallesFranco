package ar.edu.unahur.obj2.vendedores

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.collections.shouldNotBeEmpty
import io.kotest.matchers.collections.shouldNotContain
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class CentroDeDistribucionTest : DescribeSpec({
    val buenosAires = Provincia(10000000)
    val rioNegro = Provincia(100000)
    val santaFe = Provincia(1000000)
    val hurlingham = Ciudad(buenosAires)
    val moron = Ciudad(buenosAires)
    val quilmes = Ciudad(buenosAires)
    val rosario = Ciudad(santaFe)

    describe("Un centro de distribución con sus vendedores") {
        val vendedorFijo = VendedorFijo(hurlingham)
        val vendedorViajante = Viajante(listOf(rioNegro))
        val comercioCorresponsal = ComercioCorresponsal(listOf(moron,quilmes))
        val centroDeDistribucion = CentroDeDistribucion(hurlingham, mutableListOf(vendedorFijo,
            vendedorViajante,comercioCorresponsal)
        )

        describe("vendedorEstrella") {
            it("Comprobando con los vendedores que tiene de base") {
                centroDeDistribucion.vendedorEstrella().shouldBe(vendedorFijo)
            }
        }

        describe("puedeCubrirUnaCiudad") {
            it("Comprobando con los vendedores que tiene de base") {
                centroDeDistribucion.puedeCubrirUnaCiudad(rosario).shouldBeFalse()
            }
        }

        describe("vendedoresGenericos") {
            it("Comprobando con los vendedores que tiene de base") {
                centroDeDistribucion.vendedoresGenericos().shouldBeEmpty()
            }
        }

        describe("esRobusto") {
            it("Comprobando con los vendedores que tiene de base") {
                centroDeDistribucion.esRobusto().shouldBeFalse()
            }
        }
    }

    describe("Otro centro de distribución con sus vendedores") {
        val vendedorFijo1 = VendedorFijo(moron)
        val vendedorViajante1 = Viajante(listOf(santaFe))
        val comercioCorresponsal1 = ComercioCorresponsal(listOf(quilmes,hurlingham))
        val centroDeDistribucion1 = CentroDeDistribucion(moron, mutableListOf(vendedorFijo1,
            vendedorViajante1,comercioCorresponsal1)
        )

        describe("vendedorEstrella") {
            it("Comprobando con los vendedores que tiene de base") {
                vendedorFijo1.agregarCertificacion(Certificacion(true, 60))
                vendedorViajante1.agregarCertificacion(Certificacion(false, 100))
                comercioCorresponsal1.agregarCertificacion(Certificacion(false, 90))
                centroDeDistribucion1.vendedorEstrella().shouldNotBe(comercioCorresponsal1)
            }
        }

        describe("puedeCubrirUnaCiudad") {
            it("Comprobando con los vendedores que tiene de base") {
                centroDeDistribucion1.puedeCubrirUnaCiudad(rosario).shouldBeTrue()
            }
        }

        describe("vendedoresGenericos") {
            it("Comprobando con los vendedores que tiene de base") {
                    centroDeDistribucion1.vendedoresGenericos().shouldNotContain(vendedorFijo1)
            }
        }

        describe("esRobusto") {
            it("Comprobando con los vendedores que tiene de base") {
                centroDeDistribucion1.esRobusto().shouldBeTrue()
            }
        }
    }
})