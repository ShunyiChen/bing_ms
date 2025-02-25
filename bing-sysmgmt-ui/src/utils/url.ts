export function extractUrlParams(url) {
    const params = {};
    const queryString = url.split('?')[1];
    
    if (queryString) {
        const searchParams = new URLSearchParams(queryString);
        for (let [key, value] of searchParams.entries()) {
            params[key] = value;
        }
    }
    return params;
}


