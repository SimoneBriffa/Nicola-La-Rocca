package com.xantrix.webapp.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "ARTICOLI")
@Data
public class Articoli  implements Serializable
{
	private static final long serialVersionUID = 7361753083273455478L;
	
	@Id
	@Column(name = "CODART")
	@Size(min = 5, max = 20, message = "{Size.Articoli.codArt.Validation}")
	@NotNull(message = "{NotNull.Articoli.codArt.Validation}")
	private String codArt;
	
	@Column(name = "DESCRIZIONE")
	@Size(min = 6, max = 80, message = "{Size.Articoli.descrizione.Validation}")
	private String descrizione;	
	
	@Column(name = "UM")
	private String um;
	
	@Column(name = "CODSTAT")
	private String codStat;
	
	@Column(name = "PZCART")
	@Max(value = 99, message = "{Max.Articoli.pzCart.Validation}")
	private Integer pzCart;
	
	@Column(name = "PESONETTO")
	@Min(value = (long) 0.01, message = "{Min.Articoli.pesoNetto.Validation}")
	private double pesoNetto;
	
	@Column(name = "IDSTATOART")
	@NotNull(message= "{NotNull.Articoli.idStatoArt.Validation}") 
	private String idStatoArt;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "DATACREAZIONE")
	private Date dataCreaz;
	
	public Articoli() {}
	
	public Articoli(String CodArt, String Descrizione, Integer PzCart, Double PesoNetto, String IdStatoArt)
	{
		this.codArt = CodArt;
		this.descrizione = Descrizione;
		this.pzCart = PzCart;
		this.pesoNetto = PesoNetto;
		this.idStatoArt = IdStatoArt;
	}
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "articolo", orphanRemoval = true)
	@JsonManagedReference
	private Set<Barcode> barcode = new HashSet<>();
	
	@OneToOne(mappedBy = "articolo", cascade = CascadeType.ALL, orphanRemoval = true)
	private Ingredienti ingredienti;
	
	@ManyToOne
	@JoinColumn(name = "IDFAMASS", referencedColumnName = "ID")
	private FamAssort famAssort;
	
	@ManyToOne
	@JoinColumn(name = "IDIVA", referencedColumnName = "idIva")
	private Iva iva;
	
}
