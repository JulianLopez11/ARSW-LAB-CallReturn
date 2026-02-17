# ARSW-LAB-CallReturn

## Ejectutar punto sockets

1. Clonar el repositorio a la maquina local:
   ```bash
    git clone <URL_DEL_REPOSITORIO>
    ```
2. Navegar al directorio del repositorio
    ```bash
    cd <NOMBRE_DEL_PROYECTO>
    ```
3. Compilar El proyecto
    ```bash
    javac -d . src/domain/sockets/firstPart/*.java
    ```
4. En un cmd ejectutar 
    ```bash
    java domain.sockets.firstPart.SocketServer
    ```
5. Abrir otra pestaña en el cmd,, en la raiz del repositorio y ejecutar
    ```bash
    java domain.sockets.firstPart.SocketClient
    ```