import pandas as pd
import numpy as np 

def generate(columnas, filas):
    columns = [ "Caso", "Edad", "Fecha inicio", "Fecha Dx", "Sexo", "Ciudad", "Localidad", "Ubicación", "Estado", "tipo.contagio"]
    diccionario = { i: np.random.randint(100000000, size = filas) if i != 'Estado' 
    else np.random.randint(2,size = filas) for i in columns}
    return pd.DataFrame.from_dict(diccionario)

for i in range(1,8):
    generate(4,10**i).to_json('tests{}.json'.format(10**i))
