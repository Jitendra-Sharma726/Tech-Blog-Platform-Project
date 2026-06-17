# Tech-Blog-Platform-Project

# Tech Blog Platform Project

## Project Overview

The Tech Blog Platform is a Spring Boot-based backend application designed to power a content publishing website where authors can create blog posts and readers can interact through comments. The project demonstrates how to build a layered RESTful application using Spring Boot, Spring Data JPA, H2 Database, and Lombok while implementing real-world entity relationships and cascading operations.

The platform models a hierarchical blogging system consisting of three core entities:

* **Author** – Represents content creators who publish articles.
* **BlogPost** – Represents articles written by authors.
* **Comment** – Represents reader feedback on blog posts.

The application leverages JPA entity mappings to establish relationships between these entities. An Author can own multiple Blog Posts, and each Blog Post can contain multiple Comments. These relationships are configured with appropriate cascade rules and fetch strategies to ensure efficient database operations and optimal application performance.

## Key Features

### Author Management

* Create and manage authors.
* Maintain a one-to-many relationship between authors and blog posts.
* Automatically delete all posts associated with an author when the author is removed.

### Blog Post Management

* Create blog posts linked to specific authors.
* Store long-form content up to 5000 characters.
* Retrieve all published posts through a feed endpoint.
* Automatically delete associated comments when a post is removed.

### Comment Management

* Add comments to existing blog posts.
* Maintain many-to-one relationships with blog posts.
* Support eager loading of post information for contextual access.

## Technical Implementation

### Architecture

The project follows a layered architecture pattern:

1. **Entity Layer**

   * Defines database models and relationships.
   * Uses JPA annotations for ORM mapping.

2. **Repository Layer**

   * Provides CRUD operations through Spring Data JPA repositories.
   * Eliminates the need for manual SQL queries.

3. **Service Layer**

   * Contains business logic.
   * Handles entity linking and validation.
   * Manages cascading behavior.

4. **Controller Layer**

   * Exposes RESTful APIs.
   * Handles HTTP requests and responses.

### Database Design

The application uses an in-memory H2 database with the following relationship structure:

Author → BlogPost → Comment

#### Relationship Configuration

**Author → BlogPost**

* One-to-Many
* CascadeType.ALL
* FetchType.LAZY

**BlogPost → Comment**

* One-to-Many
* CascadeType.REMOVE
* FetchType.LAZY

**Comment → BlogPost**

* Many-to-One
* FetchType.EAGER

**BlogPost → Author**

* Many-to-One
* FetchType.EAGER

### Performance Optimizations

* Lazy loading prevents unnecessary retrieval of large collections.
* Eager loading ensures important contextual information is available when needed.
* JSON serialization controls prevent infinite recursion in bidirectional relationships.

## REST API Endpoints

### Authors

* POST `/api/authors`

  * Create a new author.

### Blog Posts

* POST `/api/authors/{authorId}/posts`

  * Create a post for an author.

* GET `/api/posts`

  * Retrieve all blog posts.

* DELETE `/api/posts/{postId}`

  * Delete a post and all associated comments.

### Comments

* POST `/api/posts/{postId}/comments`

  * Add a comment to a blog post.

## Learning Objectives

This project helps developers gain practical experience with:

* Spring Boot REST API development
* Spring Data JPA repositories
* Entity relationships and mappings
* Cascade operations
* Fetch strategies (LAZY and EAGER)
* Constructor-based dependency injection
* Layered architecture design
* H2 database integration
* JSON serialization management

## Expected Outcome

Upon completion, the Tech Blog Platform will function as a fully operational backend service capable of managing authors, blog posts, and comments while maintaining proper relational integrity, optimized data fetching, and automated cascading deletions through JPA.
