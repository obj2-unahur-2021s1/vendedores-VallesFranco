package ar.edu.unahur.obj2.vendedores

class CentroDeDistribucion(val ciudadDeOrigen: Ciudad, val vendedores: MutableList<Vendedor>) {

    fun agregarUnVendedor(vendedor: Vendedor) {
        if (vendedores.contains(vendedor)) {
            error("El vendedor ya está registrado en el centro de distribución.")
        }
        vendedores.add(vendedor)
    }

    fun vendedorEstrella() = vendedores.maxBy { it.puntajeCertificaciones() }

    fun puedeCubrirUnaCiudad(ciudad: Ciudad) = vendedores.any() {it.puedeTrabajarEn(ciudad)}

    fun vendedoresGenericos() = vendedores.filter { it.esGenerico() }

    fun esRobusto() = vendedores.count() { it.esFirme() } >= 3

}