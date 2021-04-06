package ar.edu.unahur.obj2.vendedores

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue

class VendedorTest : DescribeSpec({
  val misiones = Provincia(1300000)
  val sanIgnacio = Ciudad(misiones)

  describe("Vendedor fijo") {
    val obera = Ciudad(misiones)
    val vendedorFijo = VendedorFijo(obera)

    describe("puedeTrabajarEn") {
      it("su ciudad de origen") {
        vendedorFijo.puedeTrabajarEn(obera).shouldBeTrue()
      }
      it("otra ciudad") {
        vendedorFijo.puedeTrabajarEn(sanIgnacio).shouldBeFalse()
      }
    }
  }

  describe("Viajante") {
    val cordoba = Provincia(2000000)
    val villaDolores = Ciudad(cordoba)
    val viajante = Viajante(listOf(misiones))

    describe("puedeTrabajarEn") {
      it("una ciudad que pertenece a una provincia habilitada") {
        viajante.puedeTrabajarEn(sanIgnacio).shouldBeTrue()
      }
      it("una ciudad que no pertenece a una provincia habilitada") {
        viajante.puedeTrabajarEn(villaDolores).shouldBeFalse()
      }
    }

    describe("esInfluyente") {
      it("con su única provincia habilitada") {
        viajante.esInfluyente().shouldBeFalse()
      }
    }
  }

  describe("Comercio Corresponsal") {
    val buenosAires = Provincia(8000000)
    val chubut = Provincia(900000)
    val santaFe = Provincia(1500000)
    val morris = Ciudad(buenosAires)
    val laMatanza = Ciudad(buenosAires)
    val rawson = Ciudad(chubut)
    val rosario = Ciudad(santaFe)

    val comercioCorresponsal = ComercioCorresponsal(listOf(morris, laMatanza, rawson, rosario))

    describe("puedeTrabajarEn") {
      it("una ciudad donde tiene sucursal") {
        comercioCorresponsal.puedeTrabajarEn(rawson).shouldBeTrue()
      }
      it("una ciudad donde no tiene sucursal") {
        comercioCorresponsal.puedeTrabajarEn(sanIgnacio).shouldBeFalse()
      }
    }

    describe("esInfluyente") {
      it("cantidad de provincias en las que tiene sucursal") {
        comercioCorresponsal.esInfluyente().shouldBeTrue()
      }
    }
  }

})