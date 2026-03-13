def register_user(raw_data: dict) -> dict:
    """
    Validates user data, classifies user based on age, and returns response.
    
    Args:
        raw_data (dict): A dictionary representing the raw user input data.
                         Expected keys: 'firstName', 'lastName', 'email', 'phone', 'dateOfBirth'.
                         
    Returns:
        dict: A dictionary containing the registration result.
              Successful registration: {'status': 'SUCCESS', 'userType': 'NORMAL'|'DEPENDENT', 'email': '<email>'}
              Failed registration: {'status': 'ERROR', 'errors': [<list of error strings>]}
    """
    # TODO: Implement validation and classification logic here
    pass
