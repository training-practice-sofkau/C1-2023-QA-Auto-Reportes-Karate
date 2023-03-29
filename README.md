# Pruebas de Herramientas de automatización Serenity VS Karate (Servicios REST)

El objetivo de este taller es realizar pruebas automatizadas de servicios REST empleando BDD, proporcionando un
acercamiento a las herramientas disponibles, y así conocer las características o pasos necesarios para implementarlas.

**Nota:** En este repositorio encontrará una guía para la automatización, tenga en cuenta los archivos
`build.gradle` para su elaboración, se recomienda el IDE IntelliJ, y una versión Java superior a 8. Use buenas prácticas
de programación.

## Requisitos generales de la automatización

- Seleccione cuatro servicios REST de la pagina [https://reqres.in/](https://reqres.in/) (sugerencia un CRUD).
- Automatice 2 de los servicios empleando el framework de Serenity BDD.
- Automatice 2 de los servicios empleando el framework de Karate.
- Genere los reportes de la ejecución de cada framework.

### Serenity BDD

1. Explore previamente la aplicación que pretende automatizar en Postman (respuestas esperadas).
2. Defina claramente la característica que desea automatizar.
3. Emplee el uso del patrón ScreenPlay (tasks, questions, step definitions, runners, features, etc).
4. Genere un reporte con las evidencias de la ejecución.

Ejemplo del reporte:

![Untitled](Imag/Untitled.png)

- **Recursos sugeridos:**

[https://serenity-bdd.github.io/docs/screenplay/screenplay_rest](https://serenity-bdd.github.io/docs/screenplay/screenplay_rest)

[https://github.com/serenity-bdd](https://github.com/serenity-bdd)

[https://www.youtube.com/watch?v=v6o0z2qrxLk&list=PLeo6Q1inqlOeti5rRcCvKbB_WDymxcAXO](https://www.youtube.com/watch?v=v6o0z2qrxLk&list=PLeo6Q1inqlOeti5rRcCvKbB_WDymxcAXO)

### Karate

1. Explore previamente la aplicación que pretende automatizar en Postman (respuestas esperadas).
2. Defina claramente la característica que desea automatizar.
3. Genere un reporte con las evidencias de la ejecución.

Ejemplo del reporte:

![Untitled](Imag/Untitled%201.png)

- **Recurso sugerido:**

[https://github.com/karatelabs/karate](https://github.com/karatelabs/karate)

## En un repositorio entregar

1. La solución del taller (automatización).
2. Archivo de texto con las pruebas realizadas en Postman.
3. Capturas de pantalla de los reportes generados.
4. Conclusiones del taller.

Bonus James

Plugin requeridos en IntelliJ:

- Gherkin
- Cucumber for Java

- - -

# Workshop solution

The `ApiRestSerenity` and `PruebasKarate` folders contain the automations of the CRUD functions provided by the REQRES 
API. The `Reports&PostmanTest.pdf` file details the requests used, as well as defining which ones are automated with the
Serenity library and which ones with the Karate framework.

The reports of the automated executions are also attached in this file.

## Conclusions

TODO: add conclusions of the workshop

>Note: as a value-added, the entire workshop was developed in English, and seeking to write clean and readable code, 
> following KISS principle. 💋

- - -

*By: Jonathan Vargas 🐢 Ococho*

<a href="https://gitmoji.dev">
  <img
    src="https://img.shields.io/badge/gitmoji-%20😜%20😍-FFDD67.svg?style=flat-square"
    alt="Gitmoji"
  />
</a>