package com.born.ecommerce.presentation.di.scope

import javax.inject.Scope
import kotlin.annotation.AnnotationRetention
import kotlin.annotation.Retention
import kotlin.annotation.Target

/**
 * ChildFragmentScoped custom scoping annotation specifies that the lifespan of a dependency be
 * the same as that of a Child Fragment
 */
@Scope
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.TYPE, AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
annotation class ChildFragmentScoped
