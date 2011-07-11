package bo;

import java.util.List;

import pojo.Time;

public interface ITimeBO {
	public void create(Time time);

	public void update(Time time);

	public void delete(Time time);

	public Time findById(Time time);

	public List<Time> findAll();
	
	public Time findByName(String nome);
}
