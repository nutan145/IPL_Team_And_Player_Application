package com.nt.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="MSMP_IPL_PLAYER_INFO")
@Setter
@Getter
@RequiredArgsConstructor
public class IPLPlayer  implements Serializable {
	@Id
	@SequenceGenerator(name="gen1",sequenceName = "PID_SEQ",initialValue = 1,allocationSize = 1)
	@GeneratedValue(generator = "gen1",strategy = GenerationType.SEQUENCE)
	private  Integer pid;
	
	@Column(length = 20)
	@NonNull
	private String pname;
	
	private  Integer  age;
	
	@Column(length = 20)
	@NonNull
	private   String role;
	
	@NonNull
	@ManyToOne(targetEntity = IPLTeam.class,cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
	@JoinColumn(name = "t_id",referencedColumnName ="teamid") //for FK column
	private IPLTeam  team;  //To build  many to one  Association
	
	//metadata properites
	 @Version
	  private  Integer  updateCount;
	  @CreationTimestamp
	  @Column(updatable = false)
	  private   LocalDateTime  createdOn;
	  
	  @UpdateTimestamp
	  @Column(insertable = false)
	  private   LocalDateTime  lastUpdatedOn;
	  
	  @Column(length=30,updatable = false)
	  private String createdBy;
	  
	  @Column(length=30,insertable = false)
	  private String updatedBy;

	
	public  IPLPlayer() {
		System.out.println("IPLPlayer:: 0-param constructor");
	}

	@Override
	public String toString() {
		return "IPLPlayer [pid=" + pid + ", pname=" + pname + ", age=" + age + ", role=" + role + "]";
	}

}
