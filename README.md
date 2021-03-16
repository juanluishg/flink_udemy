# Flink Course From Udemy

<img src="https://upload.wikimedia.org/wikipedia/en/thumb/a/af/Apache_Flink_Logo.svg/1200px-Apache_Flink_Logo.svg.png" alt="logo_flink" width="300"/>

## Conceptos
* **Dataset:** objeto de datos para operaciones batch
* **Datatream:** objeto de datos para operaciones en streaming

Son inmutables, es decir, que una operación aplicada sobre ellos genera uno nuevo, no se pueden modificar. 

No se pueden aplicar operaciones sobre la mitad de ellos o partes, se aplica sobre todo el dataset o datastream entero. 

Cada uno contiene una lista de dependencias, es decir, de que datasets o datastreams depende, asi como se guarda un registro de las operaciones que se realiza a cada uno, para asi en caso de que un nodo se caiga poder reconstruir el estado. 

Cada dataset o datastream solo puede hacer una operación a la vez. En Java si no se le asigna a un nuevo objeto, esta operación no se ejecuta(semi-lazy). 


## Prácticas
