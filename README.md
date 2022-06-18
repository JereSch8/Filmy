![CI bitrise](https://app.bitrise.io/app/a0e6dcfba6d519ba/status.svg?token=BY-OWhYZpLNqUkD2tKFgbw&branch=master)
![Logo](https://user-images.githubusercontent.com/58143759/174452962-210b8093-d00f-45b9-bea0-696463c268d3.png) 
FILMY es una aplicación destinada a dispositivos móviles Android, donde el usuario puede observar todas las películas lanzadas hasta el momento siendo clasificadas por rating y teniendo la opción de acceder a cada título y conocer toda su información, desde actores, duración hasta una breve sinopsis de la película.


# Android - Compose - Clean architecture - MVVM - Kotlin - Coroutines - GitFlow

### **Libraries** 

#### **Android Jetpack**

* [Compose](https://developer.android.com/jetpack/compose) Conjunto de modernas herramientas de Android para crear interfaces de usuario nativa. Simplifica y acelera el desarrollo de la interfaz de usuario en Android.

* [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle) Sirve para controlar los ciclos de vida de una aplicación Android de manera optima.

* [Activity](https://developer.android.com/guide/components/activities/intro-activities) Accede a las API que admiten composición compiladas sobre Activity.

* [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) Nos permite trabajar de manera reactiva nuestros componentes.

* [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) Herramienta que facilita la implementación del patron de presentacion MVVM de forma nativa.


#### **Image**
* [Coil](https://coil-kt.github.io/coil/compose/) Utilizada para cargar imagenes desde internet de manera asincrona.

#### **HTTP**
* [Retrofit](https://square.github.io/retrofit/) Se utiliza para realizar las peticiones HTTP a la API de [TheMovieDB](https://www.themoviedb.org/).

#### **Coroutines**
* [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) Nos permite manejar funciones asincronas en Android de una manera sencilla.

#### **Dependency Injection**
* [Hilt](https://dagger.dev/hilt/) Es una biblioteca para realizar inyeccion de dependencias.


## Arquitectura [Clean architecture]
Para desarrollar esta aplicación se optó por utilizar un arquitectura orientadas a capas, en donde las capas mas internas no tienen conocimiento sobre las capas mas externas. Esto con la finalidad de obtener como resultado un codigo más desacoplado y abierto al cambio.
![img_cleanArchitecture](https://user-images.githubusercontent.com/58143759/174452252-0fcabd85-a258-4137-99e5-a897b22163bf.png)
### Capas [Layers]

#### Dominio (Domain):
* **Entities:** Las entidades son los modelos definidos que interactúan en el sistema. Estas deben ser lo suficientemente abstractas para ser usadas por múltiples aplicaciones en el negocio.
* **Use Cases:** Contienen las reglas que le dan sentido a la aplicación. Los casos de uso dirigen el flujo a las entidades y las orquestan para cumplir con el negocio.
#### Data:
* **Network:** Aquí se resuelven las peticiones a los distintos endpoint del API utilizado en el proyecto.
* **Repository:** Es un patrón el cual se encarga de decidir desde dónde se va a obtener el dato solicitado, si será a través de internet o de la copia local.
* **Data Base:** Es en donde se guardan los datos de la aplicación que son recibidos a través de internet. Aquí los datos persisten de manera local.
#### Presentation:
* **Framework:** Al desarrollar la aplicación para Android utilizamos su marco de desarrollo, el cual nos provee las clases necesarias para facilitar el desarrollo de la aplicación, esto incluye, las activities, coroutines, viewmodels, etc.
* **UI:** Es donde el usuario interactúa, realiza una acción y en base a esa interacción se disparan distintos eventos. Aquí se encuentran las Activities.
* **ViewModels:** Es la interfaz o nexo que conecta la vista, lo que el usuario observa, y toda la parte lógica que sucede por detrás
![img_layers](https://user-images.githubusercontent.com/58143759/174452740-23f89df5-4fae-4535-bcea-82f4a1ea439a.png)


## Patrón de presentación [MVVM]
El patrón de arquitectura elegido para Filmy App es el MVVM, por sus siglas en inglés Model View ViewModel, es un patrón de diseño que tiene por finalidad separar la parte de la interfaz del usuario(de ahí la V de View) de la parte de la lógica de negocio(de ahí la M de Model), logrando así que la parte visual sea totalmente independiente. El otro componente es el ViewModel que es la parte que va a interactuar como puente entre la Vista y el Modelo.
Se utilizó este patrón de presentación ya que es el que recomienda Google y viene integrado con el framework de Android.

![img_patronpresenter](https://user-images.githubusercontent.com/58143759/174455092-76050a01-bdb5-4854-bf69-d2785ce75c97.png)

## Patrones de Diseño

### Observer
El patrón Observer es un patrón de diseño de comportamiento que permite definir un mecanismo de suscripción para notificar a varios objetos sobre cualquier evento que le suceda al objeto que está observando.
Lo utilizamos para actualizar la lista de peliculas visitadas que se ve en el HomeActivity, esta lista varía a medida que visitamos una nueva película estás aparecen en tiempo real en nuestra pantalla.

### Repository
El patrón repository está relacionado con el acceso a datos y nos permite tener una abstracción de la implementación de acceso a datos en nuestras aplicaciones, de modo que nuestra lógica de negocio no conozca ni esté acoplada a la fuente de datos. En pocas palabras esto quiere decir que el repositorio actúa como un intermediario entre nuestra lógica de negocio y nuestra lógica de acceso a datos para que se centralice en un solo punto, y de esta forma se logre evitar redundancia de código. 
En nuestro caso, utilizamos el patrón para decidir si la información que se le está pidiendo se buscará en la base de datos local o en la base de datos remota. 
![img_patternrepository](https://user-images.githubusercontent.com/58143759/174457520-94185ece-4522-4dec-8722-9d599e73fe94.png)


### Singleton 
Es un patrón de diseño que permite restringir la creación de objetos de una clase a un único objeto, con la intención de garantizar que una clase tenga una única instancia y proporcionar un punto de acceso global a ella.
Utilizamos el patrón Singleton crear una única instancia de Retrofit que tendrá el URL [TheMovieDB](https://www.themoviedb.org/) base de datos de películas.

### Strategy
Strategy es un patrón de diseño de comportamiento que permite definir una familia de algoritmos, colocar cada uno de ellos en una clase separada y hacer sus objetos intercambiables.
Lo usamos para dibujar en pantalla distintos componentes en base a la respuesta obtenida desde el repositorio. 

## Wireframes

![img_wireframe](https://user-images.githubusercontent.com/58143759/174457715-5b7ddab0-6d7b-47f8-aadc-c7e96c060f76.png)
## Diagrama de componentes
![img_componentsdiagram](https://user-images.githubusercontent.com/58143759/174456982-2794d9ed-fa72-416c-bce0-a20041a2b75e.png)
## Diagrama de Contexto
![img_contextdiagram](https://user-images.githubusercontent.com/58143759/174457014-0d635cf4-fd34-429a-85d6-d3afc13bdfd5.png)
## GitFlow

Git Flow es un flujo de trabajo de Git que define diferentes tipos de ramas, con distintos fines y la interacción entre ellas.

- Rama **master** será la principal, aquí se encontrará el código estable y listo para ser lanzado. 
- Rama **dev** es la rama de desarrollo. Inicia en la última versión estable de nuestra rama master y cuando quieren desarrollar una nueva funcionalidad se crea una rama feature que parte de ésta. Los desarrolladores realizarán merges y branches tomando como punto de partida esta última.
- Rama **feature** nace cuando se quiere agregar una nueva característica o reparar algún error desde la rama dev con la nomenclatura ***feature/name_of_feature***, una vez la característica se encuentra desarrollada, esta rama se mergea a la *dev* y termina su vida útil. 
- Rama **hotfix** surge de la necesidad de actuar inmediatamente al tomar conocimiento de un comportamiento indeseado de un release, parte desde la rama master de manera tal que se trabaje sobre el release que ya está en funcionamiento con el objetivo de solucionar solamente el problema sin intervenir con lo que se esté trabajando en dev. 

![img_gitflow](https://user-images.githubusercontent.com/58143759/174457984-df6029c2-3f70-4795-86fe-1d507b6081b7.png)
## ¿Como ejecutarlo?
La forma mas facil de ejecutar el proyecto es a traves del IDE Android Studio, pero aquí unas instrucciones para poder construir el proyecto de manera local.

 * `./gradlew runApp` - Construye e instala la aplicación en el dispositivo conectado.
 * `./gradlew runUnitTests` - Se ejecutan los Unit test y test de implementación
 * `./gradlew runTestCoverage` - Genera un reporte del porcentaje de codigo cubierto con los test.
