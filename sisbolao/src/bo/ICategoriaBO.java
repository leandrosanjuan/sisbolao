package bo;

import java.util.List;

import pojo.Categoria;


public interface ICategoriaBO {
	public void create(Categoria categoria);

	public void update(Categoria categoria);

	public void delete(Categoria categoria);

	public Categoria findById(Categoria categoria);

	public List<Categoria> findAll();
}
