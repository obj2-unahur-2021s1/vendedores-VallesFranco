package ar.edu.unahur.obj2.vendedores

class Certificacion(val esDeProducto: Boolean, val puntaje: Int)

abstract class Vendedor {
  val certificaciones = mutableListOf<Certificacion>()

  abstract fun puedeTrabajarEn(ciudad: Ciudad): Boolean
  abstract fun esInfluyente(): Boolean

  fun esVersatil() =
    certificaciones.size >= 3
      && this.certificacionesDeProducto() >= 1
      && this.otrasCertificaciones() >= 1

  fun agregarCertificacion(certificacion: Certificacion) {
    certificaciones.add(certificacion)
  }

  fun esFirme() = this.puntajeCertificaciones() >= 30
  fun esGenerico() = certificaciones.any() { !it.esDeProducto }

  fun certificacionesDeProducto() = certificaciones.count { it.esDeProducto }
  fun otrasCertificaciones() = certificaciones.count { !it.esDeProducto }

  fun puntajeCertificaciones() = certificaciones.sumBy { c -> c.puntaje }
}

class VendedorFijo(val ciudadOrigen: Ciudad) : Vendedor() {
  override fun puedeTrabajarEn(ciudad: Ciudad): Boolean {
    return ciudad == ciudadOrigen
  }

  override fun esInfluyente() = false

}

class Viajante(val provinciasHabilitadas: List<Provincia>) : Vendedor() {
  override fun puedeTrabajarEn(ciudad: Ciudad): Boolean {
    return provinciasHabilitadas.contains(ciudad.provincia)
  }

  override fun esInfluyente() = provinciasHabilitadas.sumBy { it.poblacion } >= 10000000

}

class ComercioCorresponsal(val ciudades: List<Ciudad>) : Vendedor() {
  override fun puedeTrabajarEn(ciudad: Ciudad): Boolean {
    return ciudades.contains(ciudad)
  }

  override fun esInfluyente() = ciudades.size >= 5 || this.tieneSucursalesEnProvincias()

  fun tieneSucursalesEnProvincias() = ciudades.map {it.provincia}.toSet().size >= 3

}