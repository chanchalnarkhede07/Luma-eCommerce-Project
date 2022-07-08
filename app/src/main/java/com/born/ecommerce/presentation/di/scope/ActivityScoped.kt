package com.born.ecommerce.presentation.di.scope

import javax.inject.Scope

/**
 * The ActivityScoped scoping annotation specifies that the lifespan of a dependency be the same
 * as that of an Activity
 */
@Scope
@MustBeDocumented
@Retention(value = AnnotationRetention.RUNTIME)
annotation class ActivityScoped
