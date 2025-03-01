package com.example.hellocowcow.domain

/**
 * Interface used to transform DataType to DomainType
 */
interface DomainModelConvertible<DomainType> {
  /**
   * @see #Domain Layer
   * Convert Model entity to Domain Entity
   */
  fun toDomain(): DomainType
}
