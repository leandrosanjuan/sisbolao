package bo;

import java.util.List;

import pojo.Bolao;
import pojo.Usuario;

public interface IBolaoBO {
	public void create(Bolao bolao);

	public void update(Bolao bolao);

	public void delete(Bolao bolao);

	public Bolao findById(Bolao bolao);

	public List<Bolao> findAll();
	
	public List<Bolao> findByParticipant(Usuario participante);
}
