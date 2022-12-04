package com.samuel.swappbe.models

import javax.persistence.*

@Entity
@Table(name = "invoice")
class Invoice(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @Column(nullable = false)
    val name: String,
    @Column(nullable = false)
    val amount: Double,
) {
}