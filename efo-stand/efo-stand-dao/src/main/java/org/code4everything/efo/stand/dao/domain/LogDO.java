package org.code4everything.efo.stand.dao.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.code4everything.boot.base.bean.BaseDomain;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author pantao
 * @since 2019-04-16
 */
@Data
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "efo_log")
public class LogDO implements BaseDomain {

    private static final long serialVersionUID = 4691787201462323806L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(columnDefinition = "varchar(64)", nullable = false)
    private String description;

    @Column(columnDefinition = "varchar(256)", nullable = false)
    private String className;

    @Column(columnDefinition = "varchar(64)", nullable = false)
    private String methodName;

    @Column(columnDefinition = "datetime default current_timestamp", nullable = false)
    private LocalDateTime createTime;

    @Column(columnDefinition = "varchar(512)")
    private String args;

    @Column(columnDefinition = "varchar(256)")
    private String exceptionClass;

    @Column(columnDefinition = "varchar(512)")
    private String exceptionDetail;

    private Long executedTime;

    @Override
    public Serializable primaryKey() {
        return id;
    }
}
