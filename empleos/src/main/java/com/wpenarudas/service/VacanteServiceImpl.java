package com.wpenarudas.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wpenarudas.model.Vacante;
import com.wpenarudas.repository.VacanteRepo;

@Service
public class VacanteServiceImpl implements VacanteServiceInterface {

	@Autowired
	VacanteRepo repository;
	
	List<Vacante> lista = null;

	/*public VacanteServiceImpl() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
		lista = new LinkedList<Vacante>();
		try {
			// Oferta numero 1
			Vacante vacante1 = new Vacante();
			vacante1.setId((long) 1);
			vacante1.setNombre("Ingeniero de comunicaciones");
			vacante1.setDescripcion("Se solicita ingeniero para dar soporte a la intranet");
			vacante1.setFecha(sdf.parse("07-01-2021"));
			vacante1.setSalario(1200000);
			vacante1.setDestacado(1);
			vacante1.setImagen("empresa1.png");

			// Oferta numero 2
			Vacante vacante2 = new Vacante();
			vacante2.setId((long) 2);
			vacante2.setNombre("Ingeniero Mecatronico");
			vacante2.setDescripcion("Se solicita ingeniero para dar soporte a la Infraestructura");
			vacante2.setFecha(sdf.parse("04-01-2021"));
			vacante2.setSalario(1500000);
			vacante2.setDestacado(0);
			vacante2.setImagen("empresa2.png");

			// Oferta numero 3
			Vacante vacante3 = new Vacante();
			vacante3.setId((long) 3);
			vacante3.setNombre("Ingeniero Civil");
			vacante3.setDescripcion("Se solicita ingeniero para dar soporte a la Infraestructura");
			vacante3.setFecha(sdf.parse("17-11-2020"));
			vacante3.setSalario(1800000);
			vacante3.setDestacado(0);

			// Oferta numero 4
			Vacante vacante4 = new Vacante();
			vacante4.setId((long) 4);
			vacante4.setNombre("Ingeniero Ambiental");
			vacante4.setDescripcion("Se solicita ingeniero para dar soporte a la Infraestructura");
			vacante4.setFecha(sdf.parse("08-08-2020"));
			vacante4.setSalario(1200000);
			vacante4.setDestacado(1);
			vacante4.setImagen("empresa4.png");

			// Oferta numero 4
			Vacante vacante5 = new Vacante();
			vacante5.setId((long) 5);
			vacante5.setNombre("Mecánico dental");
			vacante5.setDescripcion("Se solicita mecánico dental para laborar con contrato indefinido desde la fecha");
			vacante5.setFecha(sdf.parse("28-01-2021"));
			vacante5.setSalario(1400000);
			vacante5.setDestacado(2);
			vacante5.setImagen("logo19.png");

			lista.add(vacante1);
			lista.add(vacante2);
			lista.add(vacante3);
			lista.add(vacante4);
			lista.add(vacante5);

		} catch (ParseException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}*/

	public List<Vacante> buscarTodas() {
		return (List<Vacante>) repository.findAll();
		//(List<Vacante>) 
	}

	@Override
	public Vacante buscarPorId(Long idVacante) {

		for (Vacante v : lista) {
			if (v.getId() == idVacante) {
				return v;
			}
		}

		return null;
	}

	@Override
	public void guardar(Vacante vacante) {
		lista.add(vacante);

	}

	@Override
	public Vacante crearVacante(Vacante vacante) throws Exception {
		vacante = repository.save(vacante);
		return null;
	}

}
