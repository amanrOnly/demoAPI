# demoAPI : Restaurant Mangement

## APIs 

### User

- /user/register
- /user//{userID}/placeOrder
- /user/{userID}/addReview
- /user/{id}/search
- /user/{userID}/order/{orderID}/food/{foodID}

### Restaurant

- /restaurant/register
- /restaurant/update
- /restaurant/{restaurantID}/food/{foodID}/order/{orderID}

### Food

- /food/add
- /food/getFoods
- /food/remove
- /food/deactivate

## API Playground

http://localhost:9090/swagger-ui.html

#NOTES

To be optimized
- http://localhost:9090/user/12/order/17/food/5
we can directly get order id using user id, no need request it.
