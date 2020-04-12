package cl.prez.hibernate.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedNativeQueries;
import javax.persistence.Table;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.ConstructorResult;
import javax.persistence.ColumnResult;

import cl.prez.hibernate.model.dto.BandDTO;

import lombok.Data;

@NamedNativeQueries({
    @NamedNativeQuery(
        name = "getInfoBands",
        query = "SELECT"+ 
                "    band.band_id 'band_id',"+ 
                "    band.band_name 'band_name',"+ 
                "    GROUP_CONCAT(style.style_name) 'band_styles',"+ 
                "    country.country_name 'band_country',"+ 
                "    band.band_year 'band_year' "+ 
                "FROM"+ 
                "    band"+ 
                "    LEFT JOIN country ON country.country_id = band.country_id"+ 
                "    LEFT JOIN band_style ON band_style.band_id = band.band_id"+ 
                "    LEFT JOIN style ON style.style_id = band_style.style_id "+ 
                "GROUP BY"+ 
                "    band.band_id "+ 
                "ORDER BY"+  
                "    band_name",
        resultSetMapping = "getInfoBandsMapping"
    )    
})

@SqlResultSetMapping(
    name = "getInfoBandsMapping",
    classes = @ConstructorResult(
        targetClass = BandDTO.class,
        columns = {
            @ColumnResult(name = "band_id"),
            @ColumnResult(name = "band_name"),
            @ColumnResult(name = "band_styles"),
            @ColumnResult(name = "band_country"),
            @ColumnResult(name = "band_year")
        }
    )
)

@Data
@Entity
@Table(name = "band")
public class Band {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "band_id")
    private Integer id;

    @Column(name = "band_name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @ManyToMany
    @JoinTable(
        name = "band_style",
        joinColumns = @JoinColumn(name = "band_id"),
        inverseJoinColumns = @JoinColumn(name = "style_id")
    )
    private List<Style> styles;

    @Column(name = "band_year")
    private Integer year;
}