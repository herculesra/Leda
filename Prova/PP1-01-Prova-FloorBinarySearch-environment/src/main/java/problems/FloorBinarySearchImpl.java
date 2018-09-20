package problems;

public class FloorBinarySearchImpl implements Floor {

	@Override
	public Integer floor(Integer[] array, Integer x) { 
		int meio = array.length / 2;
		int pivo = array[meio];
		int resultado = 0;
		
		if(x > pivo) {
			if(x >= array[meio +1]) {
				if(x == array[meio + 1]) {
					resultado = array[meio + 1];
				}else {
					resultado = buscaBinaria(array, x, meio + 1, array.length);
				}
			}else {
				resultado = pivo;
			}
		}else {
			if(x == pivo) {
				resultado = pivo;
			}else {
				resultado = buscaBinaria(array, x, array[0], array[meio - 1]);
			}
		}
		return resultado;
	}	

	public int buscaBinaria(Integer[] array, int x, int left, int right) {
		int meio = array.length / 2;
		int pivo = array[meio];
		int resultado = 0;
		
		if(x > pivo) {
			if(x >= array[meio +1]) {
				if(x == array[meio + 1]) {
					resultado = array[meio + 1];
				}else {
					resultado = buscaBinaria(array, x, meio + 1, array.length);
				}
			}else {
				resultado = pivo;
			}
		}else {
			if(x == pivo) {
				resultado = pivo;
			}else {
				resultado = buscaBinaria(array, x, array[0], array[meio - 1]);
			}
		}
		
		return resultado;
	}

}
