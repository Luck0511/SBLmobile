//FUNCTION TO DEFINE TABLES RELATIONSHIPS
export const defineAssociations = (dbObject) => {
    //destruct object to get models (no extra import required)
    const {
            Book,
            BookShelf,
            Shelf,
            User,
            UserBook
        }= dbObject;

    //ONE-TO-ONE RELATIONSHIPS --> we don't have any

    //ONE-TO-MANY RELATIONSHIPS --> we have 1 (user to shelf, and vice versa)

    //one user has many shelves
    User.hasMany(Shelf, {
        foreignKey: 'userID',
        as:'user'
    });
    //one shelf has one user
    Shelf.belongsTo(User, {
        foreignKey: 'userID',
        as:'shelf'
    })

    //MANY-TOM-ANY RELATIONSHIPS --> we have 2 (book to shelf & user to book, and vice versa)
    //many books have many shelves
    Book.belongsToMany(Shelf, {
        through: BookShelf,
        foreignKey: 'bookID',
        otherKey: 'shelfID',
        as:'shelves'
    });
    //many shelves have many books
    Shelf.belongsToMany(Book,{
        through: BookShelf,
        foreignKey: 'shelfD',
        otherKey: 'bookID',
        as:'books'
    });
    //many users have many books
    User.belongsToMany(Book, {
        through:UserBook,
        foreignKey: 'userID',
        otherKey: 'bookID',
        as:'books'
    });
    //many books have many users
    Book.belongsToMany(User,{
        through: UserBook,
        foreignKey: 'bookID',
        otherKey: 'userID',
        as:'users'
    });
}