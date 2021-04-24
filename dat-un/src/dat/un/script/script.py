import pandas as pd
import numpy as np 

def generate(columnas, filas):
    columns = [ 'columna {}'.format(i) for i in range(columnas)]
    diccionario = { i: np.random.randint(10000, size = filas) if i != 'columna 1' 
    else np.random.randint(2,size = filas) for i in columns}
    return pd.DataFrame.from_dict(diccionario)

for i in range(1,8):
    generate(4,10**i).to_json('tests{}.json'.format(10**i))