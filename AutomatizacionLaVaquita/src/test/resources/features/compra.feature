#language:es
#encoding:UTF-8
#Author: Ivan Dario Ruiz Bernal

Característica: Compras en pagina de pruebas
  yo como usuario de la pagina automation exercise
  quiero comprar productos
  para disfrutar de ellos

  Antecedentes:
    Dado que el usuario esta en la pagina de inicio
    Y navega hasta la el formulario de registro
    Y completa los campos para iniciar sesion
    Cuando navega hasta los productos y selecciona una marca

  @Compra
  Escenario: Compra de productos
    Y selecciona tres de ellos
    Y va al carrito de compras y hace el proceso de checkout
    Entonces se le indica al usuario que se hizo su orden